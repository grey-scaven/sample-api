package service

import entity.Sample
import entity.SampleItem
import service.dto.TestRequestDto
import spock.lang.Shared
import spock.lang.Specification

class SampleServiceTest extends Specification {

    @Shared
    SampleService sampleService

    void setupSpec() {
        sampleService = new SampleService()
    }

    void setup() {

    }

    def "Test"() {
        given:
        TestRequestDto requestDto = new TestRequestDto()
        requestDto.setCode(1L)
        requestDto.setUserId("test_user")

        def sample = new Sample(id: 1L, code: 1L, userId: "test_user")

        def sampleItem1  = new SampleItem(id: 1L, itemName: "test_item_1")
        def sampleItem2  = new SampleItem(id: 2L, itemName: "test_item_2")
        def sampleItem3  = new SampleItem(id: 3L, itemName: "test_item_3")
        def sampleItemList = Arrays.asList(sampleItem1, sampleItem2, sampleItem3)

        sample.setSampleItemList(sampleItemList)

        sampleService.getSampleByCodeForUpdate(_) >> Optional.of(sample)

        when:
        sampleService.test(requestDto)

        then:
        sample.getSampleItemList().length = 3
    }

}
