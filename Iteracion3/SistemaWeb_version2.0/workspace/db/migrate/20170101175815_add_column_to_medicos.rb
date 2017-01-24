class AddColumnToMedicos < ActiveRecord::Migration
  def change
    add_column :medicos, :activo, :boolean
  end
end
