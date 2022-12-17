package com.quan.coursepeerreview;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.quan.coursepeerreview.Entity.Account;
import com.quan.coursepeerreview.Entity.Course;
import com.quan.coursepeerreview.Entity.CourseQuestion;
import com.quan.coursepeerreview.Entity.CourseStatus;
import com.quan.coursepeerreview.Entity.PeerReview;
import com.quan.coursepeerreview.Entity.Question;
import com.quan.coursepeerreview.Entity.Role;
import com.quan.coursepeerreview.Entity.Student;
import com.quan.coursepeerreview.Repository.AccountRepos;
import com.quan.coursepeerreview.Repository.CourseQuestionsRepos;
import com.quan.coursepeerreview.Repository.CourseRepos;
import com.quan.coursepeerreview.Repository.PeerReviewRepos;
import com.quan.coursepeerreview.Repository.QuestionRepos;
import com.quan.coursepeerreview.Repository.StudentRepos;

@SpringBootApplication
public class CoursePeerReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoursePeerReviewApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(AccountRepos accountRepos, StudentRepos studentRepos, CourseRepos courseRepos, CourseQuestionsRepos courseQuestionsRepos, PeerReviewRepos peerReviewRepos, QuestionRepos questionRepos) {
		return args -> {
			Account a = new Account("quan123", new BCryptPasswordEncoder().encode("123456"), Role.ADMIN);
			Account b = new Account("quan",new BCryptPasswordEncoder().encode("123456"), Role.USER);
			accountRepos.save(a);
			accountRepos.save(b);
			// List<Account> roleList = accountRepos.findAccountByRole(Role.USER);
			// roleList.stream().forEach(acc -> System.out.println(acc));
			Student studentA = new Student("123", "quan", "doan", "ICT");
			Student studentB = new Student("1234", "khanh", "nguyen", "Medice");
			studentRepos.save(studentA);
			studentRepos.save(studentB);

			Course course1 = new Course("programming1", "123456", CourseStatus.OPEN);
			Course course2 = new Course("programming2", "123451", CourseStatus.OPEN);
			Course course3 = new Course("programming3", "123452", CourseStatus.CLOSED);
			Course course4 = new Course("programming4", "123454", CourseStatus.OPEN);

			courseRepos.save(course1);
			courseRepos.save(course2);
			courseRepos.save(course3);
			courseRepos.save(course4);

			CourseQuestion cQuestion1 = new CourseQuestion("is he/she active", course1);
			CourseQuestion cQuestion2 = new CourseQuestion("how they handle tasks", course1);
			CourseQuestion cQuestion3 = new CourseQuestion("did they organize the team work well", course2);
			CourseQuestion cQuestion4= new CourseQuestion("is he/she active", course2);

			courseQuestionsRepos.save(cQuestion1);
			courseQuestionsRepos.save(cQuestion2);
			courseQuestionsRepos.save(cQuestion3);
			courseQuestionsRepos.save(cQuestion4); 

			//PeerReview review1 = new PeerReview(studentB, course4, a);
			// PeerReview review5 = new PeerReview(studentB, course4, a);

			//PeerReview review2 = new PeerReview(studentB, course3, a);
			// PeerReview review3 = new PeerReview(studentA, course1, b);
			// PeerReview review4 = new PeerReview(studentA, course4, b);

			// peerReviewRepos.save(review1);
			// peerReviewRepos.save(review2);
			// peerReviewRepos.save(review3);
			// peerReviewRepos.save(review4);
			// peerReviewRepos.save(review5);


			// Question question1 = new Question("is she active", review2);
			// questionRepos.save(question1);
		};
	}
	@Bean
	public BCryptPasswordEncoder PasswordEncode() {
		return new BCryptPasswordEncoder(); 
	}

}
