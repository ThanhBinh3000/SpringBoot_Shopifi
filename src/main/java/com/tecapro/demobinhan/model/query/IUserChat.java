package com.tecapro.demobinhan.model.query;

import java.util.Date;

public interface IUserChat {
    Long getId();
    String getEmail();
    String getFullName();
    String getContent();
    Date getTime();
}
