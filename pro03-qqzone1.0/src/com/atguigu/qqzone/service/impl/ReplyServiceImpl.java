package com.atguigu.qqzone.service.impl;

import com.atguigu.qqzone.dao.ReplyDAO;
import com.atguigu.qqzone.pojo.HostReply;
import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.HostReplyService;
import com.atguigu.qqzone.service.ReplyService;
import com.atguigu.qqzone.service.UserBasicService;

import java.util.List;

public class ReplyServiceImpl implements ReplyService {
    private ReplyDAO replyDAO;
    private HostReplyService hostReplyService;
    private UserBasicService userBasicService;
    @Override
    public List<Reply> getRelyListByTopicId(Integer topicId) {
        List<Reply> replyList = replyDAO.getReplyList(new Topic(topicId));
        for (int i = 0; i < replyList.size(); ++i){
            Reply reply = replyList.get(i);
            //1.将关联的作者设置进去
            UserBasic author = userBasicService.getUserBasicById(reply.getAuthor().getId());
            reply.setAuthor(author);

            //2.关联的hostReply设置进去
            HostReply hostReply = hostReplyService.getHostReplyByReplyID(reply.getId());
            reply.setHostReply(hostReply);
        }
        return replyList;
    }

    @Override
    public void addReply(Reply reply) {
        replyDAO.addReply(reply);
    }

    @Override
    public void delReply(Integer replyId) {
        //1.根据id获取到reply
        Reply reply = replyDAO.getReply(replyId);
        if(reply != null){
            //2.如果reply有关联的hostReply，则应先删除hostReply
            HostReply hostReply = hostReplyService.getHostReplyByReplyID(reply.getId());
            if(hostReply != null){
                hostReplyService.delHostReply(hostReply.getId());
            }
            //3.删除reply
            replyDAO.delReply(replyId);
        }
    }

    @Override
    public void delReplyByTopic(Topic topic) {
        List<Reply> replyList = replyDAO.getReplyList(topic);
        if(replyList != null){
            for (Reply reply : replyList){
                delReply(reply.getId());
            }
        }
    }
}
