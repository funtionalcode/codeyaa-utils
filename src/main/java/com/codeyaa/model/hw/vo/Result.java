package com.codeyaa.model.hw.vo;


import lombok.Data;

import java.util.List;


/**
 * @author Funtionalcode
 * @className Result
 * @description 华为图文识别结果实体类
 * @date 2021/5/27 12:08
 */
@Data
class Result {
    String words_block_count;
    List<WordsBlock> words_block_list;

}
