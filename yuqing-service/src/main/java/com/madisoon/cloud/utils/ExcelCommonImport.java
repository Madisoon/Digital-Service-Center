package com.madisoon.cloud.utils;

import com.madisoon.starter.office.excel.AbstractExcelDataImport;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;


/**
 * Description:
 * excel导入
 *
 * @author Msater Zg
 * @date 2018/11/13 5:02 PM
 */

@Service
public class ExcelCommonImport extends AbstractExcelDataImport {
    @Override
    public List excelDataOperation(List list) {
        System.out.println(list);
        return null;
    }

    public static void main(String[] args) {
        ExcelCommonImport excelCommonImport = new ExcelCommonImport();
        try {
            excelCommonImport.excelDataImport("/Users/zg/Desktop/2017级本科生邮箱(1).xls", "2017级本科生邮箱");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "queue")
    public void receive(String payload, Channel channel,
                        @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        System.out.println("消费者打印");
        System.out.println(payload);
    }
}
