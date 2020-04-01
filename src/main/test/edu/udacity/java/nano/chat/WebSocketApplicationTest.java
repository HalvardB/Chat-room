package edu.udacity.java.nano.chat;

import edu.udacity.java.nano.WebSocketChatApplication;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.websocket.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.net.URI;
import java.nio.ByteBuffer;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
public class WebSocketApplicationTest {
    
    private Session session;
    private MockMvc mockMvc;

    @InjectMocks
    private WebSocketChatApplication application = new WebSocketChatApplication();

    @InjectMocks
    private WebSocketChatServer server = new WebSocketChatServer();

    @Before
    public void setup() throws Exception {
        
        session = new Session() {

            private String id = "123";

            @Override
            public WebSocketContainer getContainer() {
                return null;
            }

            @Override
            public void addMessageHandler(MessageHandler messageHandler) throws IllegalStateException {

            }

            @Override
            public Set<MessageHandler> getMessageHandlers() {
                return null;
            }

            @Override
            public void removeMessageHandler(MessageHandler messageHandler) {

            }

            @Override
            public String getProtocolVersion() {
                return null;
            }

            @Override
            public String getNegotiatedSubprotocol() {
                return null;
            }

            @Override
            public List<Extension> getNegotiatedExtensions() {
                return null;
            }

            @Override
            public boolean isSecure() {
                return false;
            }

            @Override
            public boolean isOpen() {
                return false;
            }

            @Override
            public long getMaxIdleTimeout() {
                return 0;
            }

            @Override
            public void setMaxIdleTimeout(long l) {

            }

            @Override
            public void setMaxBinaryMessageBufferSize(int i) {

            }

            @Override
            public int getMaxBinaryMessageBufferSize() {
                return 0;
            }

            @Override
            public void setMaxTextMessageBufferSize(int i) {

            }

            @Override
            public int getMaxTextMessageBufferSize() {
                return 0;
            }

            @Override
            public RemoteEndpoint.Async getAsyncRemote() {
                return null;
            }

            @Override
            public RemoteEndpoint.Basic getBasicRemote() {
                return new RemoteEndpoint.Basic() {
                    @Override
                    public void setBatchingAllowed(boolean b) throws IOException {

                    }

                    @Override
                    public boolean getBatchingAllowed() {
                        return false;
                    }

                    @Override
                    public void flushBatch() throws IOException {

                    }

                    @Override
                    public void sendPing(ByteBuffer byteBuffer) throws IOException, IllegalArgumentException {

                    }

                    @Override
                    public void sendPong(ByteBuffer byteBuffer) throws IOException, IllegalArgumentException {

                    }

                    @Override
                    public void sendText(String s) throws IOException {

                    }

                    @Override
                    public void sendBinary(ByteBuffer byteBuffer) throws IOException {

                    }

                    @Override
                    public void sendText(String s, boolean b) throws IOException {

                    }

                    @Override
                    public void sendBinary(ByteBuffer byteBuffer, boolean b) throws IOException {

                    }

                    @Override
                    public OutputStream getSendStream() throws IOException {
                        return null;
                    }

                    @Override
                    public Writer getSendWriter() throws IOException {
                        return null;
                    }

                    @Override
                    public void sendObject(Object o) throws IOException, EncodeException {

                    }
                };
            }

            @Override
            public String getId() {
                return this.id;
            }

            @Override
            public void close() throws IOException {

            }

            @Override
            public void close(CloseReason closeReason) throws IOException {

            }

            @Override
            public URI getRequestURI() {
                return null;
            }

            @Override
            public Map<String, List<String>> getRequestParameterMap() {
                return null;
            }

            @Override
            public String getQueryString() {
                return null;
            }

            @Override
            public Map<String, String> getPathParameters() {
                return null;
            }

            @Override
            public Map<String, Object> getUserProperties() {
                return null;
            }

            @Override
            public Principal getUserPrincipal() {
                return null;
            }

            @Override
            public Set<Session> getOpenSessions() {
                return null;
            }

            @Override
            public <T> void addMessageHandler(Class<T> aClass, MessageHandler.Partial<T> partial) throws IllegalStateException {

            }

            @Override
            public <T> void addMessageHandler(Class<T> aClass, MessageHandler.Whole<T> whole) throws IllegalStateException {

            }
        };
    }

    @Test
    public void testJoin() throws Exception {
        server.onOpen(session);
        Assert.assertEquals(1, server.getOnlineSessions().size());
    }

    @Test
    public void testChat() throws Exception {
        server.onMessage("{'username': 'Halvard', 'msg': 'Testing'}");
        Assert.assertEquals(1, server.getAllMessageCount());
    }

    @Test
    public void testLeave() throws Exception {
        server.onOpen(session);
        Assert.assertEquals(1, server.getOnlineSessions().size());

        server.onClose(session);
        Assert.assertEquals(0, server.getOnlineSessions().size());
    }
}