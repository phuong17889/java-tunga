/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import entity.Invoice;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.Session;

/**
 *
 * @author MyPC
 */
@ApplicationScoped
public class InvoiceSessionHandler {

    private int invoiceId = 0;
    private final Set<Session> sessions = new HashSet<>();
    private final Set<Invoice> invoices = new HashSet<>();

    public void addSession(Session session) {
        sessions.add(session);
        for (Invoice invoice : invoices) {
            JsonObject addMessage = createAddMessage(invoice);
            sendToSession(session, addMessage);
        }
    }

    public void removeSession(Session session) {
        sessions.remove(session);
    }

    public List getInvoices() {
        return new ArrayList<>(invoices);
    }

    public void addInvoice(Invoice invoice) {
        invoice.setId(invoiceId);
        invoices.add(invoice);
        invoiceId++;
        JsonObject addMessage = createAddMessage(invoice);
        sendToAllConnectedSessions(addMessage);
    }

    public void removeInvoice(int id) {
        Invoice invoice = getInvoiceById(id);
        if (invoice != null) {
            invoices.remove(invoice);
            JsonProvider provider = JsonProvider.provider();
            JsonObject removeMessage = provider.createObjectBuilder().add("action", "remove").add("id", id).build();
            sendToAllConnectedSessions(removeMessage);
        }
    }

    public void toggleInvoice(int id) {
        JsonProvider provider = JsonProvider.provider();
        Invoice invoice = getInvoiceById(id);
        if (invoice != null) {
            if (invoice.getStatus() == Invoice.STATUS_CANCELED) {
                //TODO chỗ này hóa ra là nó set action toggle để bật tắt, mình nên set lại action khác
            }
//            JsonObject updateMessage = provider.createObjectBuilder().add("action",)
        }
    }

    private Invoice getInvoiceById(int id) {
        for (Invoice invoice : invoices) {
            if (invoice.getId() == id) {
                return invoice;
            }
        }
        return null;
    }

    private JsonObject createAddMessage(Invoice invoice) {
        JsonProvider provider = JsonProvider.provider();
        JsonObject addMessage = provider.createObjectBuilder()
                .add("action", "add")
                .add("id", invoice.getId())
                .add("fullName", invoice.getFullName())
                .add("address", invoice.getAddress())
                .add("phone", invoice.getPhone())
                .add("email", invoice.getEmail())
                .add("token", invoice.getToken())
                .add("status", invoice.getStatus())
                .add("total", invoice.getTotal())
                .add("createdAt", invoice.getCreatedAt())
                .build();
        return addMessage;
    }

    private void sendToAllConnectedSessions(JsonObject message) {
        for (Session session : sessions) {
            sendToSession(session, message);
        }
    }

    private void sendToSession(Session session, JsonObject message) {
        try {
            session.getBasicRemote().sendText(message.toString());
        } catch (IOException ex) {
            sessions.remove(session);
            Logger.getLogger(InvoiceSessionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //TODO cần xem lại cách viết hàm for của SET MAP
    //TODO http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/HomeWebsocket/WebsocketHome.html#section9
}
