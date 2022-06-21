package com.bitcoinprice.analytics.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.bitcoinprice.analytics.repository.FeedbackRepository;
import com.bitcoinprice.dataparsing.analytic.Feedback;

@Service
@ComponentScan({ "com.bitcoinprice", "com.bitcoinprice.repository" })
public class FeedbackService {

	@Autowired
	private FeedbackRepository FeedbackRepository;

	public void insertFeedback(Feedback feedback) throws Exception {
		if (feedback.getRating().isEmpty() || feedback.text.isEmpty() || feedback.getType().isEmpty())
			return;

		// safety number;
		FeedbackRepository.insert(feedback);
	}

}
