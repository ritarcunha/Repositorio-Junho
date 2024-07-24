package io.codeforall.bootcamp.javabank.services.mock;

import io.codeforall.bootcamp.javabank.services.AccountService;
import io.codeforall.bootcamp.javabank.services.CustomerService;
import io.codeforall.bootcamp.javabank.exceptions.AccountNotFoundException;
import io.codeforall.bootcamp.javabank.exceptions.AssociationExistsException;
import io.codeforall.bootcamp.javabank.exceptions.CustomerNotFoundException;
import io.codeforall.bootcamp.javabank.exceptions.RecipientNotFoundException;
import io.codeforall.bootcamp.javabank.persistence.model.AbstractModel;
import io.codeforall.bootcamp.javabank.persistence.model.Customer;
import io.codeforall.bootcamp.javabank.persistence.model.Recipient;
import io.codeforall.bootcamp.javabank.persistence.model.account.Account;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A mock {@link CustomerService} implementation
 */
public class MockCustomerService extends AbstractMockService<Customer> implements CustomerService {

    private AccountService accountService;

    /**
     * Sets the account service
     *
     * @param accountService the account service to set
     */
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * @see CustomerService#get(Integer)
     */
    @Override
    public Customer get(Integer id) {
        return modelMap.get(id);
    }

    /**
     * @see CustomerService#getBalance(Integer)
     */
    @Override
    public double getBalance(Integer customerId) throws CustomerNotFoundException {

        Customer customer = Optional.ofNullable(modelMap.get(customerId))
                .orElseThrow(CustomerNotFoundException::new);

        return customer.getAccounts().stream()
                .mapToDouble(Account::getBalance)
                .sum();
    }

    /**
     * @see CustomerService#save(Customer)
     */
    @Override
    public Customer save(Customer customer) {

        if (customer.getId() == null) {
            customer.setId(getNextId());
        }

        modelMap.put(customer.getId(), customer);
        return customer;
    }

    /**
     * @see CustomerService#delete(Integer)
     */
    @Override
    public void delete(Integer id) throws AssociationExistsException {

        Customer customer = get(id);

        if (!customer.getAccounts().isEmpty()) {
            throw new AssociationExistsException();
        }

        modelMap.remove(id);
    }

    /**
     * @see CustomerService#list()
     */
    @Override
    public List<Customer> list() {
        return new ArrayList<>(modelMap.values());
    }

    /**
     * @see CustomerService#listRecipients(Integer)
     */
    @Override
    public List<Recipient> listRecipients(Integer id) throws CustomerNotFoundException {

        return Optional.ofNullable(modelMap.get(id))
                .orElseThrow(CustomerNotFoundException::new)
                .getRecipients();
    }

    /**
     * @see CustomerService#addRecipient(Integer, Recipient)
     */
    @Override
    public void addRecipient(Integer id, Recipient recipient) throws CustomerNotFoundException, AccountNotFoundException {

        Customer customer = Optional.ofNullable(modelMap.get(id))
                .orElseThrow(CustomerNotFoundException::new);

        if (accountService.get(recipient.getAccountNumber()) == null ||
                getAccountIds(customer).contains(recipient.getAccountNumber())) {
            throw new AccountNotFoundException();
        }

        if (recipient.getId() == null) {
            recipient.setId(getNextId());
        }

        customer.addRecipient(recipient);
    }

    /**
     * @see CustomerService#removeRecipient(Integer, Integer)
     */
    @Override
    public void removeRecipient(Integer id, Integer recipientId) throws CustomerNotFoundException, RecipientNotFoundException {

        Customer customer = Optional.ofNullable(modelMap.get(id))
                .orElseThrow(CustomerNotFoundException::new);

        Recipient recipient = null;

        for (Recipient rcpt : customer.getRecipients()) {
            if (rcpt.getId().equals(recipientId)) {
                recipient = rcpt;
            }
        }

        if (recipient == null) {
            throw new RecipientNotFoundException();
        }

        customer.removeRecipient(recipient);
    }

    private Set<Integer> getAccountIds(Customer customer) {
        List<Account> accounts = customer.getAccounts();

        return accounts.stream()
                .map(AbstractModel::getId)
                .collect(Collectors.toSet());
    }
}
