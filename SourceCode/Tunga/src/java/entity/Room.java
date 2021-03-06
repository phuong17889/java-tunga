/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import model.InvoiceTableModel;
import model.TableModel;
import utility.Helper;

/**
 *
 * @author Hoangha.FPT
 */
public class Room {

    private int id;
    private String name;
    private boolean type;

    public Room(int id, String name, boolean type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Room(String name, boolean type) {
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public List<Table> getTables() throws SQLException {
        return TableModel.findAll("WHERE roomId = " + this.id);
    }

    public List<Table> getFreeTables(Book book) throws ParseException, SQLException {
        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.SHORT, Locale.US);
        Date date = df.parse(book.getDate() + " " + book.getTime());
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, 6);
        List<InvoiceTable> listInvoiceTable = InvoiceTableModel.findAll("WHERE (fromTime <= '" + dt.format(date) + "' AND toTime >= '" + dt.format(date) + "') OR (fromTime <= '" + dt.format(cal.getTime()) + "' AND toTime >= '" + dt.format(cal.getTime()) + "')");
        if (listInvoiceTable != null) {
            String[] tableId = new String[listInvoiceTable.size()];
            for (int i = 0; i < tableId.length; i++) {
                tableId[i] = listInvoiceTable.get(i).getTableId() + "";
            }
            return TableModel.findAll("WHERE roomId = " + this.id + " AND id NOT IN (" + Helper.implode(",", tableId) + ") AND type >= " + book.getNumber() + " AND type <= " + (book.getNumber() + 3));
        } else {
            return TableModel.findAll("WHERE roomId = " + this.id + " AND type >= " + book.getNumber() + " AND type <= " + (book.getNumber() + 3));
        }
    }
}
