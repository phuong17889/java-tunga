/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import entity.Invoice;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author MyPC
 */
@ApplicationScoped
@ServerEndpoint("/websocket")
public class InvoiceSocketServer {

    @Inject
    private InvoiceSessionHandler sessionHandler;

    @OnOpen
    public void onOpen(Session session) {
        sessionHandler.addSession(session);
    }

    @OnError
    public void onError(Throwable t) {
        Logger.getLogger(InvoiceSocketServer.class.getName()).log(Level.SEVERE, null, t);
    }

    @OnClose
    public void onClose(Session session) {
        sessionHandler.removeSession(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        try (JsonReader reader = Json.createReader(new StringReader(message))) {
            JsonObject jsonMessage = reader.readObject();
            if ("add".equals(jsonMessage.getString("action"))) {
                Invoice invoice = new Invoice();
                invoice.setFullName(jsonMessage.getString("fullName"));
                invoice.setAddress(jsonMessage.getString("address"));
                invoice.setPhone(jsonMessage.getString("phone"));
                invoice.setEmail(jsonMessage.getString("email"));
                invoice.setTotal(Float.parseFloat(jsonMessage.getString("total")));
                sessionHandler.addInvoice(invoice);
            }
            if ("remove".equals(jsonMessage.getString("action"))) {
                int id = (int) jsonMessage.getInt("id");
                sessionHandler.removeInvoice(id);
            }
            if ("update".equals(jsonMessage.getString("action"))) {
                int id = (int) jsonMessage.getInt("id");
                int status = (int) jsonMessage.getInt("status");
                sessionHandler.updateInvoice(id, status);
            }
        }
    }
}
