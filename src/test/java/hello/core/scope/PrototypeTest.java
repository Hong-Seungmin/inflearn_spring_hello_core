package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean");
        PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean = " + prototypeBean);
        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean);

        ac.close();
        System.out.println("call destroy()");
        prototypeBean.destroy();
        prototypeBean1.destroy();
    }

    @Scope("prototype")
    static class PrototypeBean {
        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }

        @PostConstruct
        public void init(){
            System.out.println("SingletonBean.init");
        }
    }
}
