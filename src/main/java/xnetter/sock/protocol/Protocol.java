package xnetter.sock.protocol;

import java.util.Map;

import xnetter.sock.core.Handler;
import xnetter.sock.marshal.IMarshal;
import xnetter.sock.marshal.Octets;

/**
 * 客户端与服务器之间通信协议的基类
 * @author majikang
 * @create 2019-12-05
 */
public abstract class Protocol implements IMarshal {

    public static void encode(Protocol m, Octets bs) {
        bs.writeCompactUint(m.getTypeId());
        m.marshal(bs);
    }

    public static Protocol decode(Map<Integer, Protocol> stubs, Octets bs) {
        int type = bs.readCompactUint();
        Protocol m = stubs.get(type);
        if (m != null) {
            m.unmarshal(bs);
            return m;
        } else {
            return null;
        }
    }


    public abstract int getTypeId();
    
    private Handler handler;
    
    public void setHandler(Handler handler) {
    	this.handler = handler;
    }
    
    public Handler getHandler() {
    	return handler;
    }
}
