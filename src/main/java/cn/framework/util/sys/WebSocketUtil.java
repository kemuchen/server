package cn.framework.util.sys;


import cn.framework.Constants;
import cn.framework.system.code.MessageTypeEnum;
import cn.framework.system.dao.auto.entity.SysMessageEntity;
import cn.framework.system.service.impl.message.BusinessSysMessageServiceImplements;
import cn.framework.system.service.inter.message.BusinessSysMessageServiceInterface;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @ClassName WebSocketServer
 * @Desc
 * @Author 柯雷
 * @Date 2019/10/20 15:30
 * @Version 1.0
 */
@ServerEndpoint(value = "/websocket/{sid}")
//@Component
public class WebSocketUtil {

    static Logger logger = LoggerFactory.getLogger(WebSocketUtil.class);

    // concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketUtil> webSocketSets = new CopyOnWriteArraySet<>();

    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    // 接收sid
    private Integer sid = 0;

    /**
     * 系统消息
     */
    static BusinessSysMessageServiceInterface businessSysMessage =
            SpringUtil.getBean(BusinessSysMessageServiceImplements.class);

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") Integer sid) {
        try {
            this.session = session;
            webSocketSets.add(this);     //加入set中
            this.sid = sid;

            // 发送为发送的消息
            this.sendNotSendMessage(this.sid);
        } catch (Exception e) {
            logger.error("【WebSocketUtil.onOpen】websocket IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSets.remove(this);  //从set中删除
    }

    /*
     *收到客户端消息
     */
    @OnMessage
    public void onMessage(String action, Session session) {
        try {
            for (WebSocketUtil item : webSocketSets) {
                Map<String, Object> actionMap = JSONObject.parseObject(action);
                if (item.sid == Integer.parseInt(actionMap.get("receive_man") + "")) {
                    SysMessageEntity sysMessageEntity = new SysMessageEntity();
                    sysMessageEntity.setContent(actionMap.get("content") + "");
                    sysMessageEntity.setReceive_man(item.sid);
                    sysMessageEntity.setTitle(actionMap.get("title") + "");
                    sysMessageEntity.setMessage_type(MessageTypeEnum.MESSAGE.getType());
                    businessSysMessage.saveMessage(sysMessageEntity);
                    sendInfo(sysMessageEntity);
                }
            }
        } catch (Exception e) {
            logger.error("【WebSocketUtil.onMessage】接收客户端发送的消息异常");
        }
    }

    /**
     * 实现服务器主动推送
     */
    private void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * @Description: 群发自定义消息
     * @Params: [sysMessageEntity, users]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020-05-09 8:27
     */
    public static void sendInfoUsers(SysMessageEntity sysMessageEntity, List<Integer> users) {
        for (Integer user : users) {
            // 设置id为null
            sysMessageEntity.setId(null);
            // 设置接收人
            sysMessageEntity.setReceive_man(user);
            sendInfo(sysMessageEntity);
        }
    }

    /**
     * @Description: 发送单条自定义消息
     * @Params: [sysMessageEntity]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020-05-09 8:27
     */
    public static void sendInfo(SysMessageEntity sysMessageEntity) {
        try {
            for (WebSocketUtil item : webSocketSets) {
                if (item.sid.equals(sysMessageEntity.getReceive_man())) {
                    item.sendMessage(sysMessageEntity.getContent());
                    sysMessageEntity.setIs_send(Constants.YES);
                    sysMessageEntity.setSend_time(new Date());
                }
                item.sendMessage(JSONObject.toJSONString(sysMessageEntity));
            }
            // 保存消息
            sysMessageEntity.setCreate_user(sysMessageEntity.getReceive_man());
            sysMessageEntity.setModify_user(sysMessageEntity.getModify_user());
            businessSysMessage.saveMessage(sysMessageEntity);
        } catch (Exception e) {
            logger.error("【WebSocketServer.sendInfo】发送单条自定义消息出错：" + e);
        }
    }

    /**
     * @Description: 发送应该发送但没有发送的消息
     * @Params: [userid]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020-05-09 8:58
     */
    private void sendNotSendMessage(Integer userid) {
        try {
            // 查询所有未发送且有效的消息
            List<SysMessageEntity> sysMessageEntities =
                    (List<SysMessageEntity>) businessSysMessage.getMessagesUnSend(userid).getData();
            for (SysMessageEntity messageEntity : sysMessageEntities) {
                messageEntity.setIs_send(Constants.YES);
                messageEntity.setSend_time(new Date());
                // 更新消息发送状态
                businessSysMessage.saveMessage(messageEntity);
            }
        } catch (Exception e) {
            logger.error("【WebSocketServer.sendNotSendMessage】发送应该发送但没有发送的消息出错：" + e);
        }
    }
}
