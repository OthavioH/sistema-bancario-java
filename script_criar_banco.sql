CREATE SCHEMA IF NOT EXISTS `sistemabanco` DEFAULT CHARACTER SET utf8 ;
USE `sistemabanco` ;

-- -----------------------------------------------------
-- Table `sistemabanco`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Usuario (
  cpf INT(11) NOT NULL,
  nome VARCHAR(70) NOT NULL,
  senha VARCHAR(45) NOT NULL,
  PRIMARY KEY (cpf),
  UNIQUE INDEX id_UNIQUE (cpf ASC) 
);


-- -----------------------------------------------------
-- Table `sistemabanco`.`Conta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistemabanco`.`Conta` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `saldo` DECIMAL(11,2) NOT NULL DEFAULT 0,
  `Usuario_cpf` INT(11) NOT NULL,
  `tipo_conta` INT(1) NOT NULL,
  PRIMARY KEY (`id`, `Usuario_cpf`),
  INDEX `fk_Conta_Usuario1_idx` (`Usuario_cpf` ASC),
  UNIQUE INDEX `Usuario_cpf_UNIQUE` (`Usuario_cpf` ASC),
  UNIQUE INDEX `tipo_conta_UNIQUE` (`tipo_conta` ASC) ,
  CONSTRAINT `fk_Conta_Usuario1`
    FOREIGN KEY (`Usuario_cpf`)
    REFERENCES `sistemabanco`.`Usuario` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `sistemabanco`.`Transferencia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistemabanco`.`Transferencia` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `data_transferencia` DATETIME NOT NULL,
  `valor` DECIMAL(11,2) NOT NULL DEFAULT 0,
  `Conta_Remetente` INT(11) NOT NULL,
  `Conta_Destinatario` INT(11) NOT NULL,
  PRIMARY KEY (`id`, `Conta_Remetente`, `Conta_Destinatario`),
  INDEX `Conta_Remetente_FK_idx` (`Conta_Remetente` ASC),
  INDEX `Conta_Destinatario_FK_idx` (`Conta_Destinatario` ASC),
  CONSTRAINT `Conta_Remetente_FK`
    FOREIGN KEY (`Conta_Remetente`)
    REFERENCES `sistemabanco`.`Conta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Conta_Destinatario_FK`
    FOREIGN KEY (`Conta_Destinatario`)
    REFERENCES `sistemabanco`.`Conta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `sistemabanco`.`Emprestimo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistemabanco`.`Emprestimo` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `saldo_disponivel` DECIMAL(11,2) NOT NULL,
  `Conta_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`, `Conta_id`),
  INDEX `fk_Emprestimo_Conta1_idx` (`Conta_id` ASC),
  CONSTRAINT `fk_Emprestimo_Conta1`
    FOREIGN KEY (`Conta_id`)
    REFERENCES `sistemabanco`.`Conta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `sistemabanco`.`ContaCorrente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistemabanco`.`ContaCorrente` (
  `Conta_id` INT(11) NOT NULL,
  `Conta_Usuario_cpf` INT(11) NOT NULL,
  `limite` DECIMAL(11,2) NOT NULL DEFAULT 0,
  PRIMARY KEY (`Conta_id`, `Conta_Usuario_cpf`),
  CONSTRAINT `fk_ContaCorrente_Conta1`
    FOREIGN KEY (`Conta_id` , `Conta_Usuario_cpf`)
    REFERENCES `sistemabanco`.`Conta` (`id` , `Usuario_cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `sistemabanco`.`ContaPoupanca`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistemabanco`.`ContaPoupanca` (
  `Conta_id` INT(11) NOT NULL,
  `Conta_Usuario_cpf` INT(11) NOT NULL,
  `rendimento` DECIMAL(11,2) NOT NULL DEFAULT 0,
  PRIMARY KEY (`Conta_id`, `Conta_Usuario_cpf`),
  CONSTRAINT `fk_ContaPoupanca_Conta1`
    FOREIGN KEY (`Conta_id` , `Conta_Usuario_cpf`)
    REFERENCES `sistemabanco`.`Conta` (`id` , `Usuario_cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

