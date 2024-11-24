package net.nussi.buckshot.oracle.engine.state;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@AllArgsConstructor
@Builder
@ToString
public class GameState implements Serializable {
    @ToString.Exclude
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Builder.Default
    public int step = 0; // Each actions is a step
    @Builder.Default
    public int turn = 0; // Each time the current player swaps the counter increments
    @Builder.Default
    public int round = 0; // Each time when someone dies the counter increments
    @Builder.Default
    public boolean ended = false;
    @Builder.Default
    public long seed = 4564654645L;

    public ShotgunState shotgun;

    public PlayerState current;
    public PlayerState opponent;

    public GameState clone() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            oos.flush();
            oos.close();
            bos.close();
            byte[] byteData = bos.toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream(byteData);
            Object object = new ObjectInputStream(bais).readObject();
            return (GameState) object;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return this;
        }
    }


}
