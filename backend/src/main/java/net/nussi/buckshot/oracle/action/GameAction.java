package net.nussi.buckshot.oracle.action;

import net.nussi.buckshot.oracle.state.GameState;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class GameAction {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public GameState state;

    public GameAction(GameState state) {
        try {

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(state);
            oos.flush();
            oos.close();
            bos.close();
            byte[] byteData = bos.toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream(byteData);
            Object object = new ObjectInputStream(bais).readObject();
            this.state = (GameState) object;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public abstract GameState execute();
    public abstract String description();
}
