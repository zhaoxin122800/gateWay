package com.icbc.gateway.service;

import com.icbc.gateway.pojo.*;
import com.icbc.gateway.util.RemoteExecuteCommand;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GthService {
    /** 获得的 所有数据 遍历结果
     * @return
     * @throws Exception
     */
    // 获得数据
    public HashMap<String, List<GTH>> gthAll() throws Exception {
        //读取的XML 文件的 地址
        String pathname="D:/conf/gateWay1.xml";

        SAXReader reader = new SAXReader();
        //读取xml
        Document doc = reader.read(new File(pathname));
        // 获得跟标签 目录
        Element root = doc.getRootElement();
        // 存储数据的Map 集合
        HashMap<String, List<GTH>> oneIdDate = new HashMap<>();
        //存储list 的集合
        List<GTH> gthlist = new ArrayList<>();

        List<User> userList = this.gthTitle();
        String value = "";
        List<Element> list = root.elements();
        for (Element element : list) {
            value = element.attributeValue("value");
            List<Element> list1 = element.elements();

            for (Element li : list1) {
                GTH gw = new GTH();
                gw.setUsers(value);
                gw.setId(li.attributeValue("id"));
                gw.setName(li.elementText("name"));
                gw.setPsw(li.elementText("psw"));
                gw.setUrl(li.elementText("url"));
                gw.setStartShell(li.elementText("start"));
                gw.setResetShell(li.elementText("reset"));
                gw.setBreakShell(li.elementText("break"));
                gthlist.add(gw);
            }
        }
        for (int i = 0; i < userList.size(); i++) {

            List<GTH> li1 = new ArrayList<>();

            for (GTH gList : gthlist) {

                if (userList.get(i).getUsers().equals(gList.getUsers())) {

                    li1.add(gList);

                    oneIdDate.put(i + 1 + "组", li1);
                }
            }
        }
        return oneIdDate;
    }

    /** 读取xml 跟标签的 结果 一组 二组
     * @return
     * @throws Exception
     */
    public List<User> gthTitle() throws Exception {

        List<User> userlist = new ArrayList<>();

        SAXReader reader = new SAXReader();

        Document doc = reader.read(new File("D:/conf/gateWay1.xml"));

        Element root = doc.getRootElement();

        List<Element> list = root.elements();
        String value = "";

        for (Element element : list) {

            value = element.attributeValue("value");

            User user = new User();

            user.setUsers(value);

            userlist.add(user);
        }
        return userlist;
    }

    /** 下拉列表的 获取
     * @param users
     * @return
     * @throws Exception
     */
    public List<GTH> onclickSelect(String users) throws Exception {

        HashMap<String, List<GTH>> map = this.gthAll();

        List<GTH> list = new ArrayList<>();

        for (Map.Entry<String, List<GTH>> entry : map.entrySet()) {

            if (entry.getKey().equals(users)) {

                list.addAll(entry.getValue());
            }
        }
        return list;
    }

    /** 日志检索 的方法
     * @param s
     * @return
     */
    public ResultShell resultShell(ResultShell s) {
        try {
            Logger logger = LoggerFactory.getLogger(getClass());

            RemoteExecuteCommand rec = new RemoteExecuteCommand(s.getGth().getUrl(), s.getGth().getName(), s.getGth().getPsw());

            String ShellParameter = s.getShellParameter();
            String getShellValue =s.getShellValue();

            String ps = rec.executeSuccess("sh" + " " + s.getGth().getStartShell() + " " + ShellParameter + " " + getShellValue);

            logger.info(ps);

            if (StringUtils.isNotBlank(ps)) {

                return new ResultShell(true, ps, s.getGth(), "","");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultShell(false, "启动失败", null, "","");

    }

    /**  根据前台 勾选的id  进行 全部的日志检索
     * @param ids
     * @return
     */
    //日志全检
    public ResultShellLogInspect logInspect(Long[] ids) {
        //返回结果
        String ps ="";
        String result="";
        List<GTH> resultList = new ArrayList<>();
        try{
            //添加log4j  日志
            Logger logger = LoggerFactory.getLogger(getClass());
            // 遍历读取GTH  获得 所有数据
            Map<String,List<GTH>> map  =this.gthAll();
            List<GTH> gthlist=new ArrayList<>();
            //循环遍历Map 数据 添加到 gthlist
            for (List<GTH> value : map.values()) {
                gthlist.addAll(value);
            }
            //统计 返回为空的次数
            Integer count= 0;
            //记录返回为空的ID
            List<String> idsList = new ArrayList<>();
           // List<String> notIdsList = new ArrayList<>();
            //遍历 ids 获得id
            for (Long id: ids) {
                //遍历gthList 获得 单独的 shell 脚本 地址
                for (GTH list: gthlist) {
                    //如果 获得的id 和 list 中的 id 一样
                    if (id.equals(Long.parseLong(list.getId()))) {
                        // 把 执行的 数据 根据ID 把执行的内容给保存起来 并返回给前台
                        GTH gth=new GTH();
                        gth.setUsers(list.getUsers());
                        gth.setId(list.getId());
                        gth.setName(list.getName());
                        gth.setPsw(list.getPsw());
                        gth.setUrl(list.getUrl());
                        gth.setStartShell(list.getStartShell());
                        gth.setBreakShell(list.getBreakShell());
                        gth.setResetShell(list.getResetShell());
                        resultList.add(gth);

                        //链接linux 地址 账号 密码
                        RemoteExecuteCommand rec = new RemoteExecuteCommand(list.getUrl(), list.getName(), list.getPsw());
                        //获得 shell 脚本地址
                        String shPath = list.getStartShell();
                        //执行脚本 命令
                        ps = rec.executeSuccess("sh" + " " + shPath);
                        // 如果执行的脚本命令 返回是空
                        if (StringUtils.isBlank(ps)) {
                            // 统计为空的次数
                            count++;
                            // 在数组里添加返回为空的ID 号
                            idsList.add(list.getId());
                        }else {

                            result += "ID是"+ list.getId()+" 的执行结果为 ："+"\n"+ ps+"\n";
                        }
                        //打印日志
                        logger.info(ps);
                    }
                }

            } //要打印的返回结果
            String joint="";
            //如果 返回结果 不为0
            if(count!=0){
                for (String list :idsList){
                    //弹出框提示消息
                    joint += list +"  ";
                }
                return new ResultShellLogInspect(false, "id为 " + joint  + " 的shell脚本启动失败! " +"\n"+" \n"+result,resultList,"","");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //全部执行成功后 返回 启动成功
        return new ResultShellLogInspect(true,result,resultList,"","");

    }

    /**
     *  点击日志  执行日志的 读取 和回显日志的执行结果
     * @param ids  传回来的 多个参书ID
     * @return
     */
    public Result aKeyToRestartShell(Long[] ids) {
        try{
            //添加log4j  日志
            Logger logger = LoggerFactory.getLogger(getClass());
            // 遍历读取GTH  获得 所有数据
            Map<String,List<GTH>> map  =this.gthAll();
            List<GTH> gthlist=new ArrayList<>();
            //循环遍历Map 数据 添加到 gthlist
            for (List<GTH> value : map.values()) {
                gthlist.addAll(value);
            }
            //返回结果
            String ps ="";
            //统计 返回为空的次数
            Integer count= 0;
            //记录返回为空的ID
            List<String> idsList = new ArrayList<>();
            //遍历 ids 获得id
            for (Long id: ids) {
                //遍历gthList 获得 单独的 shell 脚本 地址
                for (GTH list: gthlist) {
                    //如果 获得的id 和 list 中的 id 一样
                    if (id.equals(Long.parseLong(list.getId()))) {
                        //链接linux 地址 账号 密码
                        RemoteExecuteCommand rec = new RemoteExecuteCommand(list.getUrl(), list.getName(), list.getPsw());
                        //获得 shell 脚本地址
                        String shPath = list.getResetShell();
                        //执行脚本 命令
                        ps = rec.executeSuccess("sh" +"/home"+ " " + shPath);
                        // 如果执行的脚本命令 返回时空
                        if (StringUtils.isBlank(ps)) {
                            idsList.add(list.getId());
                        }
                        //打印日志
                        logger.info(ps);
                    }
                }
            }
            //要打印的返回结果
            String joint="";
            //如果 返回结果 不为0
            if(count!=0){
                for (String list :idsList){
                    //弹出框提示消息
                    joint += list +"  ";
                }
                return new Result(false, "id为 " + joint  + " 的shell脚本启动失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //全部执行成功后 返回 启动成功
        return new Result(true,"重启成功");
    }

    //第四个模态框 根据第三个模态框 数据进行回显

    /**
     *  日志批量检索 执行命令
     * @param
     * @return
     */
    public ResultShellLogInspect logInspectEntityCommand(ResultShellLogInspect s) {
           String result="";
           String ps = "";
             List<GTH> resultList =new ArrayList<>();
          //  s.getMessage();
    try {
        //记录返回为空的ID
        List<String> idsList = new ArrayList<>();
        Logger logger= LoggerFactory.getLogger(getClass());
        for(GTH list:s.getGth()){
            GTH gth = new GTH();
            gth.setUsers(list.getUsers());
            gth.setId(list.getId());
            gth.setName(list.getName());
            gth.setPsw(list.getPsw());
            gth.setUrl(list.getUrl());
            gth.setStartShell(list.getStartShell());
            gth.setBreakShell(list.getBreakShell());
            gth.setResetShell(list.getResetShell());
            resultList.add(gth);


            RemoteExecuteCommand rec = new RemoteExecuteCommand(list.getUrl(), list.getName(), list.getPsw());

            String ShellParameter = s.getShellParameter();

            String getShellValue =s.getShellValue();

            ps = rec.executeSuccess("sh" + " " +list.getStartShell()+ " " + ShellParameter + " " + getShellValue);

            if (StringUtils.isBlank(ps)) {

                idsList.add(list.getId());
            }else{
                result += "执行结果ID为"+ list.getId()+" 的执行结果为 ："+"\n"+ ps+"\n";
            }
            logger.info(ps);
        }
        //统计 返回为空的次数
        Integer count= 0;
        //要打印的返回结果
        String joint="";
        //如果 返回结果 不为0
        if(count!=0) {
            for (String list : idsList) {
                //弹出框提示消息
                joint += list + "  ";
            }
            return new ResultShellLogInspect(false, "id为 " + joint  + " 的shell脚本启动失败 " +"\n"+" \n"+result,resultList,"","");
        }
    }catch (Exception e){
        e.printStackTrace();
    }
        return  new ResultShellLogInspect(true,result,resultList,"","");
    }
}

