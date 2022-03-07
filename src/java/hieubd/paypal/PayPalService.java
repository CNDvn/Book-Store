/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieubd.paypal;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import hieubd.book.BookDTO;
import hieubd.session.CartObj;
import hieubd.utils.MyContanst;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author CND
 */
public class PayPalService implements Serializable{

    public String authorizePayment(CartObj cart, String discountValue, String total)
            throws PayPalRESTException {

        Payer payer = getPayerInformation();
        RedirectUrls redirectUrls = getRedirectURLs();
        List<Transaction> listTransaction = getTransactionInformation(cart, discountValue, total);

        Payment requestPayment = new Payment();
        requestPayment.setTransactions(listTransaction);
        requestPayment.setRedirectUrls(redirectUrls);
        requestPayment.setPayer(payer);
        requestPayment.setIntent("authorize");

        APIContext apiContext = new APIContext(MyContanst.CLIENT_ID, MyContanst.CLIENT_SECRET, MyContanst.MODE);

        Payment approvedPayment = requestPayment.create(apiContext);

        return getApprovalLink(approvedPayment);

    }

    private Payer getPayerInformation() {
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setEmail("");

        payer.setPayerInfo(payerInfo);

        return payer;
    }

    private RedirectUrls getRedirectURLs() {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8084/BookStore/MainServlet?btnAction=cart");
        redirectUrls.setReturnUrl("http://localhost:8084/BookStore/MainServlet?btnAction=reviewPaymentPayPal");

        return redirectUrls;
    }

    private List<Transaction> getTransactionInformation(CartObj cart, String discountValue, String total) {

        Details detail = new Details();

        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(total);

        Transaction transaction = new Transaction();

        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<>();

        float totalReal = 0;
        for (Map.Entry<BookDTO, Integer> entry : cart.getItems().entrySet()) {
            Item item = new Item();
            item.setCurrency("USD");
            item.setName(entry.getKey().getTitle());
            item.setPrice(String.valueOf(entry.getKey().getPrice()));
            item.setQuantity(String.valueOf(entry.getValue()));
            items.add(item);
            totalReal += entry.getKey().getPrice() * entry.getValue();
        }

        detail.setShippingDiscount(String.format("%.2f", totalReal * Float.parseFloat(discountValue) / 100));
        detail.setSubtotal(totalReal + "");
        amount.setDetails(detail);

        transaction.setAmount(amount);
        transaction.setDescription("Thanks for buy my BookStore");

        itemList.setItems(items);
        transaction.setItemList(itemList);

        List<Transaction> listTransaction = new ArrayList<>();
        listTransaction.add(transaction);

        return listTransaction;
    }

    private String getApprovalLink(Payment approvedPayment) {
        List<Links> links = approvedPayment.getLinks();
        String approvalLink = null;

        for (Links link : links) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                approvalLink = link.getHref();
                break;
            }
        }

        return approvalLink;
    }

    public Payment getPaymentDetails(String paymentId) throws PayPalRESTException {
        APIContext apiContext = new APIContext(MyContanst.CLIENT_ID, MyContanst.CLIENT_SECRET, MyContanst.MODE);
        return Payment.get(apiContext, paymentId);
    }

    public Payment executePayment(String paymentId, String payerId)
            throws PayPalRESTException {
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        Payment payment = new Payment().setId(paymentId);

        APIContext apiContext = new APIContext(MyContanst.CLIENT_ID, MyContanst.CLIENT_SECRET, MyContanst.MODE);

        return payment.execute(apiContext, paymentExecution);
    }
}
