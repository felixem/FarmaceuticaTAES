package farmaceutica.taes.domainmodel.Repository.Example;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import farmaceutica.taes.domainmodel.Data.DatabaseManager;
import farmaceutica.taes.domainmodel.Data.Example.DatabaseHelperExample;
import farmaceutica.taes.domainmodel.Model.Example.Comment;

/**
 * Created by alienware18 on 9-8-13.
 */
public class CommentsRepository {

    private DatabaseHelperExample db;
    Dao<Comment, Integer> commentsDao;

    public CommentsRepository(Context ctx)
    {
        try {
            DatabaseManager dbManager = new DatabaseManager();
            //db = dbManager.getHelper(ctx);
            commentsDao = db.getCommentsDao();
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }

    }

    public int create(Comment comment)
    {
        try {
            return commentsDao.create(comment);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int update(Comment comment)
    {
        try {
            return commentsDao.update(comment);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int delete(Comment comment)
    {
        try {
            return commentsDao.delete(comment);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }

    public List<Comment> getAll()
    {
        try {
            return commentsDao.queryForAll();
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }
}
