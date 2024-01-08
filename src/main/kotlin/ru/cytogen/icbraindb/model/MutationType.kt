package ru.cytogen.icbraindb.model

enum class MutationType(private val description: String) : ModelEnum {
  HOMO_NO_MUT("Гомозигота, мутация не обнаружена"),
  HETERO_MUT_IN_ONE_CHR("Гетерозигота, мутация в одной из хромосом"),
  HOMO_MUT_IN_BOTH_CHR("Гомозигота, мутация в обеих хромосомах");

  override fun getDescription(): String {
    return description
  }
}
