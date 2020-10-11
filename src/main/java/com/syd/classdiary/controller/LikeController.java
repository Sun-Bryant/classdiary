package com.syd.classdiary.controller;

import com.syd.classdiary.entity.Event;
import com.syd.classdiary.entity.User;
import com.syd.classdiary.event.EventProducer;
import com.syd.classdiary.service.LikeService;
import com.syd.classdiary.util.CommunityConstant;
import com.syd.classdiary.util.CommunityUtil;
import com.syd.classdiary.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
public class LikeController implements CommunityConstant {

    @Autowired
    private LikeService likeService;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private EventProducer eventProducer;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(path = "/like", method = RequestMethod.POST)
    @ResponseBody
    public String like(int entityType, int entityId, int entityUserId, int postId) {
        User user = hostHolder.getUser();

        // 点赞
        likeService.like(user.getId(), entityType, entityId, entityUserId);

        // 查询某实体的点赞数量
        long likeCount = likeService.findEntityLikeCount(entityType, entityId);
        // 查询当前用户对某实体的点赞状态
        int likeStatus = likeService.findEntityLikeStatus(user.getId(), entityType, entityId);
        // 返回的结果
        Map<String, Object> map = new HashMap<>();
        map.put("likeCount", likeCount);
        map.put("likeStatus", likeStatus);

        // 触发点赞事件(点赞才触发，取消点赞不需要触发)
        if (likeStatus == 1) {
            Event event = new Event()
                    .setTopic(TOPIC_LIKE)
                    .setUserId(hostHolder.getUser().getId())
                    .setEntityType(entityType)
                    .setEntityId(entityId)
                    .setEntityUserId(entityUserId)
                    .setData("postId", postId);
            eventProducer.fireEvent(event);
        }

//        if(entityType == ENTITY_TYPE_POST) {
//            // 计算帖子分数 其实就是把有了点赞评论收藏等操作后，需要再次计算帖子分数的集合中。然后利用定时任务去计算帖子分数。
//            String redisKey = RedisKeyUtil.getPostScoreKey();
//            redisTemplate.opsForSet().add(redisKey, postId);
//        }

        return CommunityUtil.getJSONString(0, null, map);
    }

}
