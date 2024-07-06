package bank.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseMeta {
    private int page;
    private int perPage;
    private long total;
    private int pageCount;
}
