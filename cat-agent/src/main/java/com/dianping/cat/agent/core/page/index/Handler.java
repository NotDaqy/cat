package com.dianping.cat.agent.core.page.index;

import java.io.IOException;

import javax.servlet.ServletException;

import org.unidal.lookup.annotation.Inject;
import org.unidal.web.mvc.PageHandler;
import org.unidal.web.mvc.annotation.InboundActionMeta;
import org.unidal.web.mvc.annotation.OutboundActionMeta;
import org.unidal.web.mvc.annotation.PayloadMeta;

import com.dianping.cat.agent.core.CorePage;
import com.dianping.cat.agent.monitor.executors.DataSender;
import com.dianping.cat.agent.monitor.executors.TaskExecutors;
import com.dianping.cat.agent.monitor.puppet.PuppetTask;

public class Handler implements PageHandler<Context> {
	@Inject
	protected JspViewer m_jspViewer;

	@Inject
	protected DataSender m_dataSender;

	@Inject
	private TaskExecutors m_taskExecutors;

	@Inject
	private PuppetTask m_puppetTask;

	@Override
	@PayloadMeta(Payload.class)
	@InboundActionMeta(name = "index")
	public void handleInbound(Context ctx) throws ServletException, IOException {
	}

	@Override
	@OutboundActionMeta(name = "index")
	public void handleOutbound(Context ctx) throws ServletException, IOException {
		Model model = new Model(ctx);

		model.setAction(Action.VIEW);
		model.setPage(CorePage.INDEX);

		if (!ctx.isProcessStopped()) {
			m_jspViewer.view(ctx, model);
		}
	}
}
