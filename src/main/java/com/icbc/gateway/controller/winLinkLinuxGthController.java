package com.icbc.gateway.controller;

import com.icbc.gateway.pojo.GTH;
import com.icbc.gateway.pojo.Result;
import com.icbc.gateway.service.GthService;
import com.icbc.gateway.util.RemoteExecuteCommand;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("gth")
public class winLinkLinuxGthController {
    @Autowired
    private GthService gthService;

    //展示所有
    @RequestMapping(value ="/gthAll", method = RequestMethod.GET)
    public List<GTH> gthAll() throws Exception {

        return gthService.gthAll();
    }

    @RequestMapping(value = "/startShell",method = RequestMethod.GET)
    public Result startShell(String id){
       try {
           Logger logger  = LoggerFactory.getLogger(getClass());
           //读取GTH  获得 所有数据
            List<GTH> gthlist  =gthService.gthAll();
            for (GTH list:gthlist){
                if (id.equals(list.getId())){
                    RemoteExecuteCommand rec = new RemoteExecuteCommand(list.getUrl(), list.getName(), list.getPsw());
                    //获得 shell 脚本地址
                    String shPath = list.getStartShell();
                    String   ps = rec.executeSuccess("sh"+" "+shPath);
                    logger.info(ps);
                    if(StringUtils.isNotBlank(ps)){
                        return new Result(true,"启动成功");
                    }
                }
            }
     /*      RemoteExecuteCommand rec= new RemoteExecuteCommand("192.168.200.128","root","123456");
           String shPath = startShell;
           String   ps = rec.executeSuccess("sh"+" "+shPath);
           logger.info(ps);
           if(StringUtils.isNotBlank(ps)){
               return new Result(true,"启动成功");
           }*/
       }catch (Exception e){
           e.printStackTrace();
       }
        return new Result(false,"启动失败");
    }

    @RequestMapping(value = "/resetShell",method = RequestMethod.GET)
    public Result resetShell(String id) throws Exception {
        try {
            Logger logger = LoggerFactory.getLogger(getClass());
            //读取GTH  获得 所有数据
            List<GTH> gthlist  =gthService.gthAll();
            for (GTH list:gthlist) {
                if (id.equals(list.getId())) {
                    RemoteExecuteCommand rec = new RemoteExecuteCommand(list.getUrl(), list.getName(), list.getPsw());
                    String shPath = list.getResetShell();
                    String ps = rec.executeSuccess("sh" + " " + shPath);
                    logger.info(ps);
                    if (StringUtils.isNotBlank(ps)) {
                        return new Result(true,"重启成功");
                    }
                }
            }
            /*Logger logger = LoggerFactory.getLogger(getClass());
            RemoteExecuteCommand rec = new RemoteExecuteCommand("192.168.200.128", "root", "123456");
            String shPath = resetShell;
            String ps = rec.executeSuccess("sh" + " " + shPath);
            logger.info(ps);
            if(StringUtils.isNotBlank(ps)){
              return new Result(true,"重启成功");
            }*/
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result(false,"重启失败");
    }

    /**
     *
     * @param //breakShell
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/breakShell",method = RequestMethod.GET)
    public Result breakShell(String id) throws Exception {
        try {
            Logger logger = LoggerFactory.getLogger(getClass());
            //读取GTH  获得 所有数据
            List<GTH> gthlist  =gthService.gthAll();
            for (GTH list:gthlist) {
                if (id.equals(list.getId())) {
                    RemoteExecuteCommand rec = new RemoteExecuteCommand(list.getUrl(), list.getName(), list.getPsw());
                    String shPath = list.getBreakShell();
                    String ps = rec.executeSuccess("sh" + " " + shPath);
                    logger.info(ps);
                    if (StringUtils.isNotBlank(ps)) {
                            return new Result(true, "断开成功");
                        }
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            return new Result(false, "断开失败");
        }

    /**
     * @param ids 复选框 选中的  id
     * @return
     * @throws Exception
     */
    //批量重启指定的
    @RequestMapping(value = "/aKeyToRestartShell",method = RequestMethod.GET)
    public Result aKeyToRestartShell(Long[] ids) throws Exception {
        try{
            //添加log4j  日志
            Logger logger = LoggerFactory.getLogger(getClass());
            //读取GTH  获得 所有数据
            List<GTH> gthlist  =gthService.gthAll();
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
                    ps = rec.executeSuccess("sh" + " " + shPath);
                    // 如果执行的脚本命令 返回时空
                    if (StringUtils.isBlank(ps)) {
                        // 返回页面 执行结果失败  失败的id
                        //return new Result(false, "id为" + list.getId() + "的shell脚本启动失败");
                        //返回为空就+1
                        count++;
                        // 在数组里添加返回为空的ID 号
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
}

