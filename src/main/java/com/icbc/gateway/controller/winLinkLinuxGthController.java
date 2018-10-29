package com.icbc.gateway.controller;

import com.icbc.gateway.pojo.*;
import com.icbc.gateway.service.GthService;
import com.icbc.gateway.util.RemoteExecuteCommand;
import com.icbc.gateway.util.SSH2Util;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("gth")
public class winLinkLinuxGthController {
    @Autowired
    private GthService gthService;

    //展示所有
    @RequestMapping(value ="/gthAll", method = RequestMethod.GET)
    public Map<String,List<GTH>> gthAll() throws Exception {

        return gthService.gthAll();
    }

    @RequestMapping("/gthTitle")
    public List<User> gthTitle() throws Exception {
        return gthService.gthTitle();
    }

    @RequestMapping("/onclickSelect")
    public List<GTH> onclickSelect(String userid)throws Exception{
        return gthService.onclickSelect(userid);
    }



    @RequestMapping(value = "/startShell",method = RequestMethod.GET)
    public ResultShell startShell(String id){
       try {
           Logger logger = LoggerFactory.getLogger(getClass());
           //读取GTH  获得 所有数据
           Map<String, List<GTH>> map = gthService.gthAll();

           for (List<GTH> value : map.values()) {
            for (GTH list : value) {
               if (id.equals(list.getId())) {
                    //试用ssh 库链接到linux  根据 读取的 gth 地址  端口写死
                   SSH2Util ssh2Util= new SSH2Util(list.getUrl(), list.getName(),list.getPsw(), 22);
                    //链接linux 执行shell 脚本地址
                   RemoteExecuteCommand rec = new RemoteExecuteCommand(list.getUrl(), list.getName(), list.getPsw());
                   //获得 shell 脚本地址
                   String shPath = list.getStartShell();
                    // 获得的shPath 地址为/home/start1  根据/ 进行切割
                   String[]st= shPath.split("/");

                   try {
                       //localPath 为上传脚本的地址  localFile 为要上传的文件名  remotePath 为需要上传脚本的地址
                       ssh2Util.putFile("D:\\新建文件夹 (2)",st[2],"/"+st[1]);
                   } catch (Exception e) {
                       e.printStackTrace();
                       return new ResultShell(false,"系统找不到指定的文件",null,"","");
                   }


                   // 要执行shell 脚本的命令
                   String ps = rec.executeSuccess("sh" + " " + shPath);
                   logger.info(ps);
                   if (StringUtils.isNotBlank(ps)) {
                       return new ResultShell(true,ps,list,"","");
                   }
               }
           }
       }
       }catch (Exception e){
           e.printStackTrace();
       }
        return new ResultShell(false,"启动失败",null,"","");
    }

    @RequestMapping(value = "/resetShell",method = RequestMethod.GET)
    public Result resetShell(String id) throws Exception {
        try {
            Logger logger = LoggerFactory.getLogger(getClass());
            //读取GTH  获得 所有数据
            Map<String,List<GTH>> map  =gthService.gthAll();
            for (List<GTH> value : map.values()) {
             for (GTH list:value) {
                 if (id.equals(list.getId())) {
                     SSH2Util ssh2Util= new SSH2Util(list.getUrl(), list.getName(),list.getPsw(), 22);


                     RemoteExecuteCommand rec = new RemoteExecuteCommand(list.getUrl(), list.getName(), list.getPsw());
                     String shPath = list.getResetShell();

                     String[]st= shPath.split("/");
                     try {
                         ssh2Util.putFile("D:\\新建文件夹 (2)",st[2],"/"+st[1]);
                     } catch (Exception e) {
                         e.printStackTrace();
                         return new Result(false,"系统找不到指定的文件");
                     }


                     String ps = rec.executeSuccess("sh" + " " + shPath);
                     logger.info(ps);
                     if (StringUtils.isNotBlank(ps)) {
                         return new Result(true, "重启成功");
                     }
                 }
             }
            }
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
    public Result breakShell(String id) {
        try {
            Logger logger = LoggerFactory.getLogger(getClass());
            //读取GTH  获得 所有数据
            Map<String,List<GTH>> map  =gthService.gthAll();
            for (List<GTH> value : map.values()) {
                for (GTH list:value) {
                    if (id.equals(list.getId())) {

                        SSH2Util ssh2Util= new SSH2Util(list.getUrl(), list.getName(),list.getPsw(), 22);

                    RemoteExecuteCommand rec = new RemoteExecuteCommand(list.getUrl(), list.getName(), list.getPsw());
                    String shPath = list.getBreakShell();

                        String[]st= shPath.split("/");
                        try {
                            ssh2Util.putFile("D:\\新建文件夹 (2)",st[2],"/"+st[1]);
                        } catch (Exception e) {
                            e.printStackTrace();
                            return new Result(false,"系统找不到指定的文件");
                        }

                        String ps = rec.executeSuccess("sh" + " " + shPath);
                    logger.info(ps);
                    if (StringUtils.isNotBlank(ps)) {
                        return new Result(true, "断开成功");
                    }
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
            return gthService.aKeyToRestartShell(ids);
    }

    //一键执行项目日志检查
    @RequestMapping("/logInspect")
    public ResultShellLogInspect logInspect(Long[] ids){
            return gthService.logInspect(ids);

    }


    @RequestMapping("/shellCommand")
    public ResultShell shellCommand(@RequestBody ResultShell s ){
      return  gthService.resultShell(s);
    }


    @RequestMapping("/twoShellCommand")
    public ResultShell twoShellCommand(@RequestBody ResultShell s ){
         return    gthService.resultShell(s);
    }

    @RequestMapping("/logInspectEntityCommand")
    public ResultShellLogInspect logInspectEntityCommand(@RequestBody ResultShellLogInspect s){
        return   gthService.logInspectEntityCommand(s);
    }

    @RequestMapping("/twoLogInspectEntityCommand")
    public ResultShellLogInspect twoLogInspectEntityCommand(@RequestBody ResultShellLogInspect s){
        System.out.println(s);
        return  gthService.logInspectEntityCommand(s);
    }

}

