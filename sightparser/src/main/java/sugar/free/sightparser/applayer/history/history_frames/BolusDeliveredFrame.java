package sugar.free.sightparser.applayer.history.history_frames;

import lombok.Getter;
import sugar.free.sightparser.applayer.descriptors.HistoryBolusType;
import sugar.free.sightparser.applayer.history.HistoryFrame;
import sugar.free.sightparser.pipeline.ByteBuf;

@Getter
public class BolusDeliveredFrame extends HistoryFrame {

    private static final long serialVersionUID = 1L;

    private HistoryBolusType bolusType;
    private float immediateAmount;
    private float extendedAmount;
    private short duration;
    private short bolusId;

    @Override
    public void parse(ByteBuf byteBuf) {
        bolusType = HistoryBolusType.getBolusType(byteBuf.readShort());
        immediateAmount = ((float) byteBuf.readShortLE()) / 100F;
        extendedAmount = ((float) byteBuf.readShortLE()) / 100F;
        duration = byteBuf.readShortLE();
        byteBuf.shift(4);
        bolusId = byteBuf.readShortLE();
    }
}