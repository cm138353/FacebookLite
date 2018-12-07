package interfaces;
import collections.User;
import com.mongodb.client.MongoDatabase;

public interface UserInter {
    public String validate(User user , MongoDatabase database);
}
