package cu.uci.coj.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

@Component
class ContextEventHandler implements ApplicationListener<ContextClosedEvent> {
    @Autowired(required=false) ThreadPoolTaskScheduler scheduler;

    public void onApplicationEvent(ContextClosedEvent event) {
    }       
}