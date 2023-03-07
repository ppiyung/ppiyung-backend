package org.ppiyung.ppiyung.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service
public class TestServiceImpl implements TestService {

	private final PlatformTransactionManager transactionManager;
	public TestServiceImpl(PlatformTransactionManager transactionManager) {
	    this.transactionManager = transactionManager;
	  }
	
	@Autowired
	private TestDao dao;
	
	@Override
	public boolean registerNewAccount(MemberTestVO newMember) {
		int affectedRows = 0;
		TransactionStatus txStatus =
		        transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try {
			affectedRows = dao.insert(newMember);
			transactionManager.commit(txStatus);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			transactionManager.rollback(txStatus);
			return false;
		}
	}

	@Override
	public MemberTestVO login(MemberTestVO member) {
		return dao.login(member);
	}
}
