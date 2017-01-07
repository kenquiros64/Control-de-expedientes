class CreateTelefonos < ActiveRecord::Migration
  def change
    create_table :telefonos do |t|
      t.string :telefono
      t.belongs_to :paciente, index: true, foreign_key: true

      t.timestamps null: false
    end
  end
end
