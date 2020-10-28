import org.junit.Test;

public class HelloFriendTest {
        @Test
        public void testHelloFriend() {
            HelloFriend helloFriend = new HelloFriend();
            String results = helloFriend.sayHelloToFriend("Maven");
            System.out.println(results);
        }
    }
