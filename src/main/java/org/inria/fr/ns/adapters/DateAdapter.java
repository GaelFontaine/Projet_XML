package org.inria.fr.ns.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DateAdapter extends XmlAdapter<String, LocalDate>{

    DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd/MM/yyyy");
    @Override
    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v,formatter);
    }
    @Override
    public String marshal(LocalDate v) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }
}

    /*
    private final DatatypeFactory datatypeFactory;

    public DateAdapter() throws DatatypeConfigurationException {
        this.datatypeFactory = DatatypeFactory.newInstance();;
    }

    @Override
    public LocalDate unmarshal(XMLGregorianCalendar xmlDate) throws Exception {
        return LocalDate.of(xmlDate.getYear(), xmlDate.getMonth(), xmlDate.getDay());
    }

    @Override
    public XMLGregorianCalendar marshal(LocalDate date) throws Exception {
        return datatypeFactory.newXMLGregorianCalendarDate(date.getYear(), date.getMonth().getValue(), date.getDayOfMonth(),  DatatypeConstants.FIELD_UNDEFINED);
    }*/

    /*
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");

    @Override
    public String unmarshal(LocalDate v) throws Exception {
        return v.format(formatter);
    }

    @Override
        public LocalDate marshal(String v) throws Exception {
            if (v != null) {
                return LocalDate.parse(v);
            } else {
                return null;
            }
        }

    }*/

        /*
    @Override
    public LocalDate unmarshal(Date xmlDate) throws Exception {
        Date utilDate = xmlDate.toGregorianCalendar().getTime();
        return LocalDateTime.ofInstant( utilDate.toInstant(), ZoneId.systemDefault() ).toLocalDate();
    }

    @Override
    public Date marshal(LocalDate date) throws Exception {
        Date utilDate = Date.from( date.atStartOfDay( ZoneId.systemDefault() ).toInstant() );
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(utilDate);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
    }*/

    /*
    private final DatatypeFactory datatypeFactory;
    public DateAdapter() throws DatatypeConfigurationException {
        this.datatypeFactory = DatatypeFactory.newInstance();
    }

    @Override
    public LocalDate unmarshal(Date xmlDate) throws Exception {
        return LocalDate.of(xmlDate.getYear(), xmlDate.getMonth(), xmlDate.getDay());    }

    @Override
    public Date marshal(LocalDate date) throws Exception {
        return datatypeFactory.newXMLGregorianCalendarDate(date.getYear(), date.getMonth().getValue(), date.getDayOfMonth(),  DatatypeConstants.FIELD_UNDEFINED);
    }*/


