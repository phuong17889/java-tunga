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
import model.InvoiceModel;

/**
 *
 * @author MyPC
 */
@ApplicationScoped
public class InvoiceSessionHandler {

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
                .add("status", invoice.getStatus())
                .add("notify", invoice.getNotify())
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

    public void addInvoice(Invoice invoice) {
        InvoiceModel.update(invoice.getId(), invoice);
        invoices.add(invoice);
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

    public void updateInvoice(int id, Invoice invoice) {
        JsonProvider provider = JsonProvider.provider();
        InvoiceModel.update(invoice.getId(), invoice);
        JsonObject updateMessage = provider.createObjectBuilder().add("action", "update").add("id", invoice.getId()).build();
        sendToAllConnectedSessions(updateMessage);
    }
}
