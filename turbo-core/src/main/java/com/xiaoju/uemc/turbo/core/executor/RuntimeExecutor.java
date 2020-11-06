package com.xiaoju.uemc.turbo.core.executor;

import com.didiglobal.reportlogger.LoggerFactory;
import com.didiglobal.reportlogger.ReportLogger;
import com.xiaoju.uemc.modules.support.jedis.RedisUtils;
import com.xiaoju.uemc.turbo.core.common.RuntimeContext;
import com.xiaoju.uemc.turbo.core.dao.InstanceDataDAO;
import com.xiaoju.uemc.turbo.core.dao.NodeInstanceDAO;
import com.xiaoju.uemc.turbo.core.dao.NodeInstanceLogDAO;
import com.xiaoju.uemc.turbo.core.util.StrongUuidGenerator;

import javax.annotation.Resource;

/**
 * Created by Stefanie on 2019/12/1.
 */
public abstract class RuntimeExecutor {

    protected static final ReportLogger LOGGER = LoggerFactory.getLogger(RuntimeExecutor.class);

    @Resource
    protected ExecutorFactory executorFactory;

    @Resource
    protected InstanceDataDAO instanceDataDAO;

    @Resource
    protected NodeInstanceDAO nodeInstanceDAO;

    @Resource
    protected NodeInstanceLogDAO nodeInstanceLogDAO;

    protected final RedisUtils redisClient = RedisUtils.getInstance();

    private static final StrongUuidGenerator idGenerator = StrongUuidGenerator.getInstance();

    protected String genId() {
        return idGenerator.getNextId();
    }

    public abstract void execute(RuntimeContext runtimeContext) throws Exception;

    public abstract void commit(RuntimeContext runtimeContext) throws Exception;

    public abstract void rollback(RuntimeContext runtimeContext) throws Exception;

    protected abstract boolean isCompleted(RuntimeContext runtimeContext) throws Exception;

    protected abstract RuntimeExecutor getExecuteExecutor(RuntimeContext runtimeContext) throws Exception;

    protected abstract RuntimeExecutor getRollbackExecutor(RuntimeContext runtimeContext) throws Exception;
}