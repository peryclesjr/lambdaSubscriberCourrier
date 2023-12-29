import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.rabbitmq.client.*;

public class AwsCourrierChallengeLambda implements RequestHandler<SQSEvent, Void> {

    @Override
    public Void handleRequest(SQSEvent event, Context context) {
        try {
            // Conectar ao RabbitMQ
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("host_do_amazonmq");
            // Adicione mais configurações de conexão, se necessário
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            // Consumir mensagem
            GetResponse response = channel.basicGet("nome_da_fila", true);
            if (response != null) {
                String message = new String(response.getBody(), "UTF-8");
                // Processar a mensagem e gravar no DynamoDB
                gravarNoDynamoDB(message);
            }

            channel.close();
            connection.close();
        } catch (Exception e) {
            context.getLogger().log("Erro: " + e.getMessage());
        }
        return null;
    }

    private void gravarNoDynamoDB(String message) {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("DeliveryTransaction");
        Item item = new Item()
                .withPrimaryKey("id", "unique_id") // Substitua com a chave primária adequada
                .withString("message", message);

        table.putItem(item);
    }
}
