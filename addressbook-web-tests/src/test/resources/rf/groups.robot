*** Settings ***
Library   ru.stqa.pft.addressbook.rf.AddressbookKeywords
Suite Setup   Init Application Manager
Suite Teardown   Stop Application Manager


*** Test Cases ***
Can Create Group With Valid Data
    ${old_count} =   Get Group Count
    Create Group   test1 name   test2 header   test3 footer
    ${new_count} =   Get Group Count
    ${expected_count} =   Evaluate   ${old_count}+1
    Should Be Equal As Integers   ${new_count}   ${expected_count}
