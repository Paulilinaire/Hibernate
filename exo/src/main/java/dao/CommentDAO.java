package dao;

import model.Comment;

import java.util.List;

public interface CommentDAO {

    public void addComment(Comment comment, long id);

    public Comment getCommentById(long id);
}
