package edu.moravian.BattleshipBot;
import edu.moravian.BattleshipBot.exceptions.SecretsException;
import edu.moravian.BattleshipBot.exceptions.StorageException;
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

    public static RedisStorage createStorage() {
        String redisHost = System.getenv().getOrDefault("REDIS_HOST", "localhost");
        int redisPort = Integer.parseInt(System.getenv().getOrDefault("REDIS_PORT", "6379"));

        RedisStorage storage = null;
        try {
            storage = new RedisStorage(redisHost, redisPort);
            storage.testConnection();
        } catch (StorageException e) {
            System.err.println("Failed to connect to Redis.");
            System.exit(1);
        }

        return storage;
    }

    private static BotResponder createResponder(BattleshipGameStorage storage) {
        BattleshipGame game = new BattleshipGame(storage);
        return new BotResponder(game);
    }


    private static String loadToken() {
        try {
            String secretName = "220_Discord_Token";
            String secretKey = "DISCORD_TOKEN";

            Secrets secrets = new Secrets();

            String secret = secrets.getSecret(secretName, secretKey);
            System.out.println(secret);
            return secret;
        } catch (SecretsException e) {
            System.out.println(e.getMessage());
            System.exit(1);
            return null;
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