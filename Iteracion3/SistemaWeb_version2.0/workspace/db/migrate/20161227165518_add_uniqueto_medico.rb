class AddUniquetoMedico < ActiveRecord::Migration
  def change
    add_index_options :medicos, :codigo, :unique => true
    add_index_options :medicos, :cedula, :unique => true
  end
end
