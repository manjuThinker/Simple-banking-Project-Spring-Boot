package com.demo_bank_v1.Controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.demo_bank_v1.models.Account;
import com.demo_bank_v1.models.PaymentHistory;
import com.demo_bank_v1.models.TransactionHistory;
import com.demo_bank_v1.models.User;
import com.demo_bank_v1.repository.AccountRepository;
import com.demo_bank_v1.repository.PaymentHistoryRepository;
import com.demo_bank_v1.repository.TransactHistoryRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/app")
public class AppController {
	@Autowired
	private AccountRepository accountRepository;
	
	   @Autowired
	    private PaymentHistoryRepository paymentHistoryRepository;

	    @Autowired
	    private TransactHistoryRepository transactHistoryRepository;

	    User user;

	@GetMapping("/dashboard")
	public ModelAndView getDashboard(HttpSession session) {
		ModelAndView getDashboardPage = new ModelAndView("dashboard");

		// Get the details of the logged i user:

		User user = (User) session.getAttribute("user");
		List<Account> getUserAccounts = accountRepository.getUserAccountsById(user.getUser_id());

		// Get Balance:
		BigDecimal totalAccountsBalance = accountRepository.getTotalBalance(user.getUser_id());

		// Set Objects:
		getDashboardPage.addObject("userAccounts", getUserAccounts);
		getDashboardPage.addObject("totalBalance", totalAccountsBalance);

		return getDashboardPage;
	}
	
	 @GetMapping("/payment_history")
	    public ModelAndView getPaymentHistory(HttpSession session){
	        // Set View:
	        ModelAndView getPaymentHistoryPage = new ModelAndView("paymentHistory");

	        // Get Logged In User:\
	        user = (User) session.getAttribute("user");

	        // Get Payment History / Records:
	        List<PaymentHistory> userPaymentHistory = paymentHistoryRepository.getPaymentRecordsById(user.getUser_id());

	        getPaymentHistoryPage.addObject("payment_history", userPaymentHistory);

	        return getPaymentHistoryPage;

	    }


	    @GetMapping("/transact_history")
	    public ModelAndView getTransactHistory(HttpSession session){
	        // Set View:
	        ModelAndView getTransactHistoryPage = new ModelAndView("transactHistory");

	        // Get Logged In User:\
	        user = (User) session.getAttribute("user");

	        // Get Payment History / Records:
	        List<TransactionHistory> userTransactHistory = transactHistoryRepository.getTransactionRecordsById(user.getUser_id());

	        getTransactHistoryPage.addObject("transact_history", userTransactHistory);

	        return getTransactHistoryPage;

	    }


}
