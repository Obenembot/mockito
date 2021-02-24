package com.sam.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sam.MockotoAndJunitApplication;
import com.sam.repositories.StudentRepository;
import com.sam.services.StudentService;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS) //this will disrupt the way spring handles things
@SpringBootTest(classes = MockotoAndJunitApplication.class)
public class StudentTest {
    
    private static final Logger logger = LoggerFactory.getLogger(StudentTest.class);
    
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    private MockMvc studentResourceMockMvc;
    
    private Student student;
    private ObjectMapper objectMapper;
    private String json;
    
    @BeforeEach
    public void setUp() {
        this.studentResourceMockMvc = webAppContextSetup(webApplicationContext).build();
        this.student = this.studentRepository.findAll().get(0);
    }
    
    @Test
    @DisplayName("Create / Save Student")
    public void before() throws Exception {
        
        Student student = new Student("TABI Oben", "TABI Oben", "TABI@gmail.com");
        
        objectMapper = new ObjectMapper();
        json = objectMapper.writeValueAsString(student);
        logger.info(json);
        
        studentResourceMockMvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }
    
    @Test
    @DisplayName("Update Student")
    public void save() throws Exception {
        logger.info("started saving... == SIZE = " + studentRepository.findAll().size());
        logger.info("Update STUDNET ====  " + student);
        
        student.setEmail("OBENEMBOT@GMAIL.COM");
        student.setName("OBEN OBEN");
        student.setSurname("OBEN OBEN");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(student);
        
        studentResourceMockMvc.perform(put("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }
    
    @Test
    @DisplayName("All Students")
    public void test() throws Exception {
        this.studentResourceMockMvc.perform(get("/api/students"))
                .andExpect(status().isOk());
    }
    
    @Test
    @DisplayName("Get Student By Id")
    public void getById() throws Exception {
        this.studentResourceMockMvc.perform(get("/api/students/" + this.student.getId())).andExpect(status().isOk());
    }
    
    @Test
    @DisplayName("Delete By Id")
    public void deleteById() throws Exception {
        this.studentResourceMockMvc.perform(delete("/api/students/" + student.getId())).andExpect(status().isOk());
    }
    
    @Test
    @DisplayName("Search By Pageable")
    public void search() throws Exception {
        String url = "/api/_searchstudents?page=" + 0 + "&size=" + 6;
        this.studentResourceMockMvc.perform(get(url)).andExpect(status().isOk());
    }
}
