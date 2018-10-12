package com.icbc.gateway.service;

import com.icbc.gateway.pojo.navigation;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class navigationService {

    public List<navigation> navigationAll() throws Exception {

        // 实例化一个类用于添加xml文件
        List<navigation> list = new ArrayList<navigation>();

        SAXReader reader = new SAXReader();
        //获取xml的地址
        Document doc = reader.read(new File("src/conf/navigationController.xml"));
        // 读取指定标签
        Iterator<Element> eleit = doc.getRootElement().elementIterator("user");
        //循环遍历 向实体类里添加数据
        while (eleit.hasNext()) {
            Element next = eleit.next();
            navigation gw = new navigation();
            gw.setName(next.attributeValue("name"));

        }
        return list;
    }
}
