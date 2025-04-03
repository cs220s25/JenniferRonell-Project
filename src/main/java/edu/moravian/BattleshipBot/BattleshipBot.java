package edu.moravian.BattleshipBot;
import edu.moravian.BattleshipBot.exceptions.StorageException;
import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvException;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class BattleshipBot {


    public static void main(String[] args) {
        BattleshipGameStorage storage = createStorage();
        BotResponder responder = createResponder(storage);
        String token = loadToken();
        startBot(responder, token);
        }

        public static RedisStorage createStorage()
        {
            RedisStorage storage = null;
            try
            {
                storage = new RedisStorage("localhost", 6379);
                storage.testConnection();
            }
            catch (StorageException e)
            {
                System.err.println("Failed to connect to Redis.");
                System.exit(1);
            }

            return storage;
        }

        private static BotResponder createResponder(BattleshipGameStorage storage)
        {
            BattleshipGame game = new BattleshipGame(storage);
            return new BotResponder(game);
        }


        private static String loadToken()
        {
            try
            {
                Dotenv dotenv = Dotenv.load();
                return dotenv.get("DISCORD_TOKEN");
            }
            catch(DotenvException e)
            {
                System.err.println("Failed to load .env file.");
                System.exit(1);
                return null;  // needed for the compiler
            }
        }

        private static void startBot(BotResponder responder, String token)
        {
            JDA api = JDABuilder.createDefault(token).enableIntents(GatewayIntent.MESSAGE_CONTENT).build();

            api.addEventListener(new ListenerAdapter()
            {
                @Override
                public void onMessageReceived(MessageReceivedEvent event)
                {
                    if (event.getAuthor().isBot())
                        return;

                    if (!event.getChannel().getName().equals("battleship-bot"))
                        return;

                    String username = event.getAuthor().getName();
                    String message = event.getMessage().getContentRaw();

                    String response = responder.response(username, message);
                    event.getChannel().sendMessage(response).queue();
                }
            });
        }
    }