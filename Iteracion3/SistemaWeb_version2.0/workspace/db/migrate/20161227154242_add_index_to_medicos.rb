class AddIndexToMedicos < ActiveRecord::Migration
  def change
    add_column :medicos, :codigo, :string, null: false
    add_column :medicos, :nombre, :string, null: false
    add_column :medicos, :apellido1, :string, null: false
    add_column :medicos, :apellido2, :string, null: false
    add_column :medicos, :cedula, :string,  null: false
    add_column :medicos, :nacionalidad, :string,  null: false
  end
end
