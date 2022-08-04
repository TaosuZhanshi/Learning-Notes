package com.myssm.ioc;

public interface BeanFactory {
    /**
     *
     * @param id
     * @return
     */
    Object getBean(String id);
}
