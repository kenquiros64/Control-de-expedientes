class ChangeNameColumnGeneroToSexo < ActiveRecord::Migration
  def change
    rename_column :paciente, :genero, :sexo
  end
end
