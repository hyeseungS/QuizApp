package com.example.quizapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 정답 보기를 선택했을 때
        selectCorrectAns()

        // 오답 보기를 선택했을 때
        selectWrongAns()

        // 정답 확인 버튼 클릭
        checkAns()
    }

    private fun selectCorrectAns() {
        binding.btnMainCorrect.setOnClickListener {
            // 오답 보기가 선택된 상태라면
            if (binding.btnMainWrong.isSelected) {
                binding.btnMainWrong.isSelected = false // 오답 보기 선택 해제
                binding.btnMainWrong.setTextColor(ContextCompat.getColor(this, R.color.gray_text))
            }
            binding.btnMainCorrect.isSelected = true // 정답 보기 선택
            binding.btnMainCorrect.setTextColor(ContextCompat.getColor(this, R.color.white))
        }
    }

    private fun selectWrongAns() {
        binding.btnMainWrong.setOnClickListener {
            // 정답 보기가 선택된 상태라면
            if (binding.btnMainCorrect.isSelected) {
                binding.btnMainCorrect.isSelected = false // 정답 보기 선택 해제
                binding.btnMainCorrect.setTextColor(ContextCompat.getColor(this, R.color.gray_text))
            }
            binding.btnMainWrong.isSelected = true // 오답 보기 선택
            binding.btnMainWrong.setTextColor(ContextCompat.getColor(this, R.color.white))
        }
    }

    private fun checkAns() {
        binding.btnMainCheck.setOnClickListener {

            // 정답 or 오답 보기를 선택한 경우
            if (binding.btnMainCorrect.isSelected || binding.btnMainWrong.isSelected) {
                binding.btnMainCheck.text = "" // 정답 확인 글자 제거

                // 정답을 선택한 경우
                if (binding.btnMainCorrect.isSelected) {
                    binding.tvMainQuiz.text = "정답이에요!"
                    binding.imgMain.setImageResource(R.drawable.correct)

                    binding.btnMainCorrect.setBackgroundResource(R.drawable.background_btn_correct)
                } else { // 오답을 선택한 경우
                    binding.tvMainQuiz.text = "정답이...아니에요"
                    binding.imgMain.setImageResource(R.drawable.wrong)

                    binding.btnMainCorrect.setBackgroundResource(R.drawable.background_btn_wrong)
                }

                binding.imgMain.layoutParams.width = 400
                binding.btnMainWrong.setBackgroundResource(R.drawable.background_btn_gray)
                binding.btnMainCorrect.setTextColor(Color.WHITE)
                binding.btnMainWrong.setTextColor(ContextCompat.getColor(this, R.color.gray_soft))
            }

            // 아무것도 선택하지 않은 경우 -> 정답 확인 버튼 비활성화
            else {
                Toast.makeText(applicationContext, "정답을 선택해주세요", Toast.LENGTH_LONG).show()
            }
        }
    }
}