package ru.sberbank.homework.dergun.task_03;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

public class ServiceImpl extends Service {
    protected ServiceImpl(URL wsdlDocumentLocation, QName serviceName) {
        super(wsdlDocumentLocation, serviceName);
    }
}
