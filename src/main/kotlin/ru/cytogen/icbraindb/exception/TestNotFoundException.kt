package ru.cytogen.icbraindb.exception

import ru.cytogen.icbraindb.model.dto.questionnaire.test.TestDto

class TestNotFoundException : NotFoundException {
    constructor(testID: Int) : super("Test by id: $testID not found")

    constructor(request: TestDto) : super("Test by $request not found")
}