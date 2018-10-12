package com.icbc.gateway.service;

import com.icbc.gateway.pojo.GTH;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class GthService {

    public List<GTH> gthAll() throws Exception {

        // 实例化一个类用于添加xml文件
        List<GTH> list = new ArrayList<GTH>();

        SAXReader reader = new SAXReader();
        //获取xml的地址
        // /conf/gateWay.xml
        /// /home/conf/gateWay.xml
        Document doc = reader.read(new File("/conf/gateWay.xml"));
        // 读取指定标签
        Iterator<Element> eleit = doc.getRootElement().elementIterator("user");
        //
       /* doc.getRootElement().*/
        //循环遍历 向实体类里添加数据
        while (eleit.hasNext()) {
            Element next = eleit.next();
            GTH gw = new GTH();
            //获得标签为id 的 元素值
            gw.setId(next.elementText("id"));
            gw.setName(next.elementText("name"));
            gw.setPsw(next.elementText("psw"));
            gw.setUrl(next.elementText("url"));
            gw.setStartShell(next.elementText("start"));
            gw.setResetShell(next.elementText("reset"));
            gw.setBreakShell(next.elementText("break"));
            list.add(gw);
        }
        return list;
    }

}
