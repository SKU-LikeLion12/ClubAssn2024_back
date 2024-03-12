//package likelion12.puzzle.controller;
//
//import likelion12.puzzle.DTO.JoinClubDTO;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Collections;
//import java.util.List;
//
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(CMManageController.class)
//public class CMManageControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private CMManageService cmManageService;
//
//    @Test
//    public void search_ReturnsCMManageDtoList_WhenKeywordIsValid() throws Exception {
//        // given
//        String keyword = "testKeyword";
//        List<JoinClubDTO> mockResult = Collections.singletonList(
//                new JoinClubDTO("1", "Test Student", "Test Club")
//        );
//
//        given(cmManageService.searchByKeyword(keyword)).willReturn(mockResult);
//
//        // when & then
//        mockMvc.perform(get("/api/search")
//                        .param("keyword", keyword)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$[0].studentId").value(mockResult.get(0).getStudentId()))
//                .andExpect(jsonPath("$[0].studentName").value(mockResult.get(0).getStudentName()))
//                .andExpect(jsonPath("$[0].joinClubs").value(mockResult.get(0).getJoinClubs()));
//    }
//}
