package com.work.mr.web.rest;

import com.work.mr.MimarathiApp;

import com.work.mr.domain.Mword;
import com.work.mr.repository.MwordRepository;
import com.work.mr.service.MwordService;
import com.work.mr.service.dto.MwordDTO;
import com.work.mr.service.mapper.MwordMapper;
import com.work.mr.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the MwordResource REST controller.
 *
 * @see MwordResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MimarathiApp.class)
public class MwordResourceIntTest {

    private static final String DEFAULT_MWORD = "AAAAAAAAAA";
    private static final String UPDATED_MWORD = "BBBBBBBBBB";

    private static final String DEFAULT_MEANING = "AAAAAAAAAA";
    private static final String UPDATED_MEANING = "BBBBBBBBBB";

    private static final Integer DEFAULT_WORDLENGTH = 1;
    private static final Integer UPDATED_WORDLENGTH = 2;

    private static final String DEFAULT_FIRST = "AAAAAAAAAA";
    private static final String UPDATED_FIRST = "BBBBBBBBBB";

    private static final String DEFAULT_SECOND = "AAAAAAAAAA";
    private static final String UPDATED_SECOND = "BBBBBBBBBB";

    private static final String DEFAULT_THIRD = "AAAAAAAAAA";
    private static final String UPDATED_THIRD = "BBBBBBBBBB";

    private static final String DEFAULT_FOURTH = "AAAAAAAAAA";
    private static final String UPDATED_FOURTH = "BBBBBBBBBB";

    private static final String DEFAULT_FIFTH = "AAAAAAAAAA";
    private static final String UPDATED_FIFTH = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_VALID = false;
    private static final Boolean UPDATED_IS_VALID = true;

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    @Autowired
    private MwordRepository mwordRepository;

    @Autowired
    private MwordMapper mwordMapper;

    @Autowired
    private MwordService mwordService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMwordMockMvc;

    private Mword mword;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MwordResource mwordResource = new MwordResource(mwordService);
        this.restMwordMockMvc = MockMvcBuilders.standaloneSetup(mwordResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Mword createEntity(EntityManager em) {
        Mword mword = new Mword()
                .mword(DEFAULT_MWORD)
                .meaning(DEFAULT_MEANING)
                .wordlength(DEFAULT_WORDLENGTH)
                .first(DEFAULT_FIRST)
                .second(DEFAULT_SECOND)
                .third(DEFAULT_THIRD)
                .fourth(DEFAULT_FOURTH)
                .fifth(DEFAULT_FIFTH)
                .isValid(DEFAULT_IS_VALID)
                .status(DEFAULT_STATUS);
        return mword;
    }

    @Before
    public void initTest() {
        mword = createEntity(em);
    }

    @Test
    @Transactional
    public void createMword() throws Exception {
        int databaseSizeBeforeCreate = mwordRepository.findAll().size();

        // Create the Mword
        MwordDTO mwordDTO = mwordMapper.mwordToMwordDTO(mword);

        restMwordMockMvc.perform(post("/api/mwords")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mwordDTO)))
            .andExpect(status().isCreated());

        // Validate the Mword in the database
        List<Mword> mwordList = mwordRepository.findAll();
        assertThat(mwordList).hasSize(databaseSizeBeforeCreate + 1);
        Mword testMword = mwordList.get(mwordList.size() - 1);
        assertThat(testMword.getMword()).isEqualTo(DEFAULT_MWORD);
        assertThat(testMword.getMeaning()).isEqualTo(DEFAULT_MEANING);
        assertThat(testMword.getWordlength()).isEqualTo(DEFAULT_WORDLENGTH);
        assertThat(testMword.getFirst()).isEqualTo(DEFAULT_FIRST);
        assertThat(testMword.getSecond()).isEqualTo(DEFAULT_SECOND);
        assertThat(testMword.getThird()).isEqualTo(DEFAULT_THIRD);
        assertThat(testMword.getFourth()).isEqualTo(DEFAULT_FOURTH);
        assertThat(testMword.getFifth()).isEqualTo(DEFAULT_FIFTH);
        assertThat(testMword.isIsValid()).isEqualTo(DEFAULT_IS_VALID);
        assertThat(testMword.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createMwordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mwordRepository.findAll().size();

        // Create the Mword with an existing ID
        Mword existingMword = new Mword();
        existingMword.setId(1L);
        MwordDTO existingMwordDTO = mwordMapper.mwordToMwordDTO(existingMword);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMwordMockMvc.perform(post("/api/mwords")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingMwordDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Mword> mwordList = mwordRepository.findAll();
        assertThat(mwordList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMwords() throws Exception {
        // Initialize the database
        mwordRepository.saveAndFlush(mword);

        // Get all the mwordList
        restMwordMockMvc.perform(get("/api/mwords?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mword.getId().intValue())))
            .andExpect(jsonPath("$.[*].mword").value(hasItem(DEFAULT_MWORD.toString())))
            .andExpect(jsonPath("$.[*].meaning").value(hasItem(DEFAULT_MEANING.toString())))
            .andExpect(jsonPath("$.[*].wordlength").value(hasItem(DEFAULT_WORDLENGTH)))
            .andExpect(jsonPath("$.[*].first").value(hasItem(DEFAULT_FIRST.toString())))
            .andExpect(jsonPath("$.[*].second").value(hasItem(DEFAULT_SECOND.toString())))
            .andExpect(jsonPath("$.[*].third").value(hasItem(DEFAULT_THIRD.toString())))
            .andExpect(jsonPath("$.[*].fourth").value(hasItem(DEFAULT_FOURTH.toString())))
            .andExpect(jsonPath("$.[*].fifth").value(hasItem(DEFAULT_FIFTH.toString())))
            .andExpect(jsonPath("$.[*].isValid").value(hasItem(DEFAULT_IS_VALID.booleanValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    public void getMword() throws Exception {
        // Initialize the database
        mwordRepository.saveAndFlush(mword);

        // Get the mword
        restMwordMockMvc.perform(get("/api/mwords/{id}", mword.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mword.getId().intValue()))
            .andExpect(jsonPath("$.mword").value(DEFAULT_MWORD.toString()))
            .andExpect(jsonPath("$.meaning").value(DEFAULT_MEANING.toString()))
            .andExpect(jsonPath("$.wordlength").value(DEFAULT_WORDLENGTH))
            .andExpect(jsonPath("$.first").value(DEFAULT_FIRST.toString()))
            .andExpect(jsonPath("$.second").value(DEFAULT_SECOND.toString()))
            .andExpect(jsonPath("$.third").value(DEFAULT_THIRD.toString()))
            .andExpect(jsonPath("$.fourth").value(DEFAULT_FOURTH.toString()))
            .andExpect(jsonPath("$.fifth").value(DEFAULT_FIFTH.toString()))
            .andExpect(jsonPath("$.isValid").value(DEFAULT_IS_VALID.booleanValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMword() throws Exception {
        // Get the mword
        restMwordMockMvc.perform(get("/api/mwords/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMword() throws Exception {
        // Initialize the database
        mwordRepository.saveAndFlush(mword);
        int databaseSizeBeforeUpdate = mwordRepository.findAll().size();

        // Update the mword
        Mword updatedMword = mwordRepository.findOne(mword.getId());
        updatedMword
                .mword(UPDATED_MWORD)
                .meaning(UPDATED_MEANING)
                .wordlength(UPDATED_WORDLENGTH)
                .first(UPDATED_FIRST)
                .second(UPDATED_SECOND)
                .third(UPDATED_THIRD)
                .fourth(UPDATED_FOURTH)
                .fifth(UPDATED_FIFTH)
                .isValid(UPDATED_IS_VALID)
                .status(UPDATED_STATUS);
        MwordDTO mwordDTO = mwordMapper.mwordToMwordDTO(updatedMword);

        restMwordMockMvc.perform(put("/api/mwords")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mwordDTO)))
            .andExpect(status().isOk());

        // Validate the Mword in the database
        List<Mword> mwordList = mwordRepository.findAll();
        assertThat(mwordList).hasSize(databaseSizeBeforeUpdate);
        Mword testMword = mwordList.get(mwordList.size() - 1);
        assertThat(testMword.getMword()).isEqualTo(UPDATED_MWORD);
        assertThat(testMword.getMeaning()).isEqualTo(UPDATED_MEANING);
        assertThat(testMword.getWordlength()).isEqualTo(UPDATED_WORDLENGTH);
        assertThat(testMword.getFirst()).isEqualTo(UPDATED_FIRST);
        assertThat(testMword.getSecond()).isEqualTo(UPDATED_SECOND);
        assertThat(testMword.getThird()).isEqualTo(UPDATED_THIRD);
        assertThat(testMword.getFourth()).isEqualTo(UPDATED_FOURTH);
        assertThat(testMword.getFifth()).isEqualTo(UPDATED_FIFTH);
        assertThat(testMword.isIsValid()).isEqualTo(UPDATED_IS_VALID);
        assertThat(testMword.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingMword() throws Exception {
        int databaseSizeBeforeUpdate = mwordRepository.findAll().size();

        // Create the Mword
        MwordDTO mwordDTO = mwordMapper.mwordToMwordDTO(mword);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMwordMockMvc.perform(put("/api/mwords")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mwordDTO)))
            .andExpect(status().isCreated());

        // Validate the Mword in the database
        List<Mword> mwordList = mwordRepository.findAll();
        assertThat(mwordList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMword() throws Exception {
        // Initialize the database
        mwordRepository.saveAndFlush(mword);
        int databaseSizeBeforeDelete = mwordRepository.findAll().size();

        // Get the mword
        restMwordMockMvc.perform(delete("/api/mwords/{id}", mword.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Mword> mwordList = mwordRepository.findAll();
        assertThat(mwordList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Mword.class);
    }
}
